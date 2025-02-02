
# define HASH_PRIME1 151
# define HASH_PRIME2 163
# define HT_INITIAL_BASE_SIZE 50
# define UP_LOAD 70
# define DOWN_LOAD 10

//--------------------- Part 1: Structure Definition-----------------------

typedef struct{
    char* key;
    char* value;
}ht_item;

typedef struct{
    int base_size;
    int size;
    int count;
    ht_item** buckets;  // an array stores the pointers to ht_item
}ht_hash_table;

