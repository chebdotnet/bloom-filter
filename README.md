# Bloom filter's implementation

## Deployment
    mvn clean compile -- to build project
    mvn clean assembly:assembly -- to generate jar file with included dependencies

## Usage
*  create filter with predefined type and hashes count to use, by default hashes count is 4, maximum value of hashes is 127
   
    `BloomFilter<Integer> bloomFilter = new BloomFilter<>(5);`
    
    `BloomFilter<String> bloomFilter = new BloomFilter<>(5);`
    
    `BloomFilter<Integer> bloomFilter = new BloomFilter<>();`
    
    
*  add necessary value into filter
    `bloomFilter.add(T)` or `bloomFilter.addAll(List<T>)`
    
*  check value is added to the filter   
    `bloomFilter.probablyContain(T)` -> always returns false if a value isn't in filter,
    probably returns true if a value added into the filter 
    

## Tech stack used to the implementation
    java 11, maven, junit, lombok, apache commons lang3, google guava and netflix hollow
