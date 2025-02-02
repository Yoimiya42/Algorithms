# include <string.h>
# include <stdlib.h>

# include "hash_table.h"
# include "prime.h"

// --------------------- Part 2: Initialization --------------------

// static means this function can only be used in this file
static ht_item* initItem(const char* k, const char* v)
{
    ht_item* newItem = malloc(sizeof(ht_item));    
    // automatically allocate memory for the string, but need to be freed manually
    newItem->key = strdup(k); 
    newItem->value = strdup(v); 

    return newItem;
}

static ht_hash_table* initNewHt(const int base_size)
{
    ht_hash_table* ht = xmalloc(sizeof(ht_hash_table));
    ht->base_size = base_size;
    ht->size = next_prime(ht->base_size);
    ht->count = 0;
    ht->buckets = xcalloc((size_t)ht->size, sizeof(ht_item*));

    return ht;
}

ht_hash_table* initHt()
{
    return initNewHt(HT_INITIAL_BASE_SIZE);
}

// --------------------- Part 3: Resize ----------------------------

static void resize(ht_hash_table* ht, const int base_size)
{
    if(base_size < HT_INITIAL_BASE_SIZE)
    {
        return;
    }

    ht_hash_table* newHt = initNewHt(base_size);
    for(int i = 0; i < ht->size;i++)
    {
        ht_item * item = ht->buckets[i];
        if(item != NULL && item != &TOMBSTONE)
        {
            insert(newHt, item->key, item->value);
        }
    }

    ht->base_size = newHt->base_size;
    ht->count = newHt->count;

    const int tempSize = ht->size;
    ht->size = newHt->size;
    newHt->size = tempSize;

    ht_item** tempBuckets = ht->buckets;
    ht->buckets = newHt->buckets;
    newHt->buckets = tempBuckets;

    delHt(newHt);
} 

static void resizeUp(ht_hash_table* ht)
{
    const int newSize = ht->base_size * 2;
    resize(ht, newSize);
}

static void resizeDown(ht_hash_table* ht)
{
    const int newSize = ht->base_size / 2;
    resize(ht, newSize);
}

//----------------------- Part 4: Deconstruction -----------------

static void delItem(ht_item* item)
{
    free(item->key);
    free(item->value);
    free(item);
}

void delHt(ht_hash_table* ht)
{   
    for(int i = 0; i < ht->size; i++)
    {
        ht_item* item = ht->buckets[i];
        if(item != NULL)
        {
            delItem(item);
        }
    }

    free(ht->buckets);
    free(ht);
}

// ---------------------- Part 5: HashFunction-----------------------

static int hashFunc(const char* k, const int prime, const int size)
{
    long hash = 0;
    const int length = strlen(k);

    for(int i = 0; i < length; i++)
    {   
        /* Hash Pattern:
         * prime           : a prime number that larger the size of the hash table
         * length - (i + 1): the reversed position of the character
         * k[i]            : the ASCII value of the character
        */

        // 1. convert string into a large integer
        hash += (long)pow(prime, length - (i + 1)) * k[i];
        // 2. reduce the size of the integer to a 'fixed range' by taking its 'reminder' mod 'size'
        hash = hash % size;
    }
    
    return (int)hash;
}

// ------------------ Part 6: Handle Collisions --------------------
// Open Addressing with Double Hashing

static int doubleHash(const char* k, const int size, const int attempt)
{
    const int hashA = hashFunc(k, HASH_PRIME1, size);
    const int hashB = hashFunc(k, HASH_PRIME2, size);

    return ( hashA + (attempt * (hashB + 1)) ) % size;
}

// ----------------- Part 7: Hash Methods --------------------------

// 1. Insert
void insert(ht_hash_table* ht, const char* key, const char* value)
{   
    const int load = ht->count * 100 / ht->size;
    if(load > 70)  
        resizeUp(ht);

    ht_item* newItem = initItem(key, value);
    int index = doubleHash(newItem->key, ht->size, 0);

    ht_item* cur = ht->buckets[index];
    int i = 1;

    while(cur != NULL) // with collision
    {   
        if(strcmp(cur->key, key) == 0)
        {
            delItem(cur);
            ht->buckets[index] = newItem;
            ht->count++;
            return;
        }

        index = doubleHash(newItem->key, ht->size, i);
        cur = ht->buckets[index];
        i++;
    }

    ht->buckets[index] = newItem;
    ht->count++;
}

// 2. Search
char* search(ht_hash_table* ht, const char* key)
{
    int index = doubleHash(key, ht->size, 0);
    ht_item* item = ht->buckets[index];

    int i = 1;
    while(item != NULL)
    {   
        // without collision
        if(item != &TOMBSTONE)
        {
            if(strcmp(item->key, key) == 0) // strcmp() returns 0 if two strings are equal
            {
                return item->value;
            }
        }

        // with collision
        index = doubleHash(key, ht->size, i);
        item = ht->buckets[index];
        i++;
    }

    return NULL;
}

// 3. Delete
// Instead of deleting the item actually, which may make finding items in the tail of the chain impossible, we mark an item as 'deleted' by replacing it with a pointer to a global sentinel represents a deleted item.

// The global sentinel
static ht_item TOMBSTONE = {NULL, NULL};

void delete(ht_hash_table* ht, const char* key)
{   
    int index = doubleHash(key, ht->size, 0);
    ht_item* item = ht->buckets[index];

    int i = 1;
    while(item != NULL)
    {
        if(item != &TOMBSTONE)
        {
            if(strcmp(item->key, key) == 0)
            {
                delItem(item);
                ht->buckets[index] = &TOMBSTONE;
                ht->count--;
            }
        }

        index = doubleHash(key, ht->size, i);
        item = ht->buckets[index];
        i++;
    }

    const int load = ht->count * 100 / ht->size;
    if(load < 10)
        resizeDown(ht);
}