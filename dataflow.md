# Pipeline
Computation flow graph

# PCollections

## Bounded PCollections:
- TextIO Source/Sink
- BigQueryIO Source/Sink
- DatastoreIO Source/Sink

## Unbounded PCollections
- PubsubIO Source/Sink
- BigQueryIO Sink

## Timestamp
Each element in a PCollection has an associated timestamp. It can be overriden by a simple ParDo transform. Unbounded sources often use timestamp for time of addition in PCollection.

## Windowing
Unbouned PCollections are always streaming and must be windowed so they can be processed. Timestamp are used for windowing

## Trigers
Dataflow uses a mechanism called triggers to determine when "enough" data has been collected in a window, after which it emits the aggregated results of that window. The default trigger emits results
when system watermark (expected time when all data should be present) has been passed and all late
data is discarded.

## Multiple PCollections
You can combine or split multiple pcollections

### Types
#### Time-based triggers:
 These triggers operate on a time reference--either event time (as indicated by the timestamp on each data element) or the processing time (the time when the data element is processed at any given stage in the pipeline).
#### Data-driven triggers:
 These triggers operate by examining the data as it arrives in each window and firing when a data condition that you specify is met. For example, you can set a trigger to emit results from a window when that window has received a certain number of data elements.
#### Composite triggers:
 These triggers combine multiple time-based or data-driven triggers in some logical way. You can set a composite trigger to fire when all triggers are met (logical AND), when any trigger is met (logical OR), etc.

# Transforms
Transformation functions

## Core Transforms
Builtin transforms like ParDo and groupbykey

## Composite Transforms
complex custom transforms

## Root Transforms
For reading data from external source.

```
    Pipeline p = Pipeline.create(PipelineOptionsFactory.fromArgs(args));

    p.apply(TextIO.Read.from("path"))   // Read input.
     .apply(new CountWords())               // Do some processing.
     .apply(TextIO.Write.to("path"));   // Write output.
     
     p.run();
```

by default all data is in global window even for unbounded collections

## ParDo
- Use to filter, map, reduce or compute some thing based on every value
- c.element() for getting value and c.output(value) for emitting value
- You can specify side inputs for each value using `ParDo.withSideInputs(view)`
- And use sideInput using `c.sideInput(view)`
- You can output more than one output collection from ParDo in that case you will get
`PCollectonTuple` as output

## GroupByKey
- Groups key value pairs by key
- You can also join multiple collections of key/value pairs, when those collections share a common key (even if the value types are different)
- If there are windows then it partition it also based on windows along with keys

## Combine
- Reduce the collection to single value collection
- In case of windowing a single value collection is generated for each window
- You can first groupByKey and then combine complete collection. As this is a common scenerio
there is a `Combine.<TYPE>PerKey` for doing both operation in one go
- When creating your own combine make sure its both communitative and assosiative because combine 
can be used to first combine for partial result and then combine those partial results
- Simple Combine:
```
  public static class SumInts implements SerializableFunction<Iterable<Integer>, Integer> {
    @Override
    public Integer apply(Iterable<Integer> input) {
      int sum = 0;
      for (int item : input) {
        sum += item;
      }
      return sum;
    }
  }
```
- Complex combine must give implementation of following
    - Create Accumulator
    - Add Input
    - Merge Accumulators
    - Extract Output
- There are also predefined combinators like: min, max, sum and mean

## Builtin Transforms
- Simple transfroms: Keys, Values, KvSwap, MapElements, FlatMapElements, Filter, and Partition
- Thera re other complex transforms which you can look up

# Pipline I/O

