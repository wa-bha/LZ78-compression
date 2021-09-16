# LZ78 Compression Algorithm
> Jesse Reyneke Barnard (1351388) | Bhavit Wadhwa (1516846)

[LZ78 compression algorithm](https://en.wikipedia.org/wiki/LZ77_and_LZ78) is a file compression and decompression tool ***to reduce file sizes*** by encoding input then packing individual bits together to enable high compression. LZ78 compression makes use of an efficient trie data structure which is effective at storing pattern repetitions.

### Quick Use Guide:
##### Compression:
```
cat [INPUT_FILENAME] | java LZencode | java LZpack > [OUTPUT_FILENAME]
```

```
cat MobyDick.txt | java LZencode | java LZpack > CompressedMobyDick.txt
```


##### Decompression:
```
cat [COMPRESSED_INPUT_FILENAME] | java LZencode | java LZpack > [OUTPUT_FILENAME]
```

```
Test Case example for full use
cat CompressedMobyDick.txt | java LZencode | java LZpack > MobyDick.txt
```
