# Testing
Warning solely based on my rough understanding, nothing scientific!!!

## Unit testing
Test one single unit, can be a class, interface, function...
### Example: 
`convert` and `merge` in product-feed
### Key points:
- Should test only 1 thing
- ideally you should not have to setup things for unit testing
- tests are there to make you think about how you can structure your code, if you have to set things up maybe your code is not that much testable
- tests are a kind of documentation which tells new people how they can use this code, i.e what is the public interface to use this peice of code
- by same reasoning private functions dont need testing
- overall it enables a workflow where you do a small change run tests, see if everything is ok and move on and before pushing pr you actually run whole application to see if it looks good
- and yes it helps to write dummy functions and then write tests and then implement actual functions

## Integration testing
Test if multiple things work correctly TOGETHER!

### Examples:
#### Rough example
`RecommendationsServiceIT` in serving layer
- we set things up and then run our whole service 
- then test it
- what if I have service which depend on other services

#### Less Rough example
`RecommendationsServiceIT` in routing layer
- Setup a mock server
- mock request response and then test your whole service
- if test fails what do you do? which part exactly failed??

#### Midocre Example
`setup_qp_test` in client lib
- there is no file name setup_qp so what are we testing?

#### Unit Testing sense
- `silly.py` example
- unit tests are mostly not for catching bugs
- how are you going to test it? you have to mock stuff?
- what if request fails, retry?
- how many jobs this function has
- general variable refactoring
- 

# General takeaway
- Tests and refactoring are very taste dependant, everyone does it a bit different but its most of time its easy to agree on good code and good tests.
- be a nice guy and leave every file in a better state than it was before
- if you are not spending huge amount of time on testing and refactoring I personally think there is a problem


# Defs
## Stub, Mock, Spy
if you get this return that and expect foo to be called x times with a,b,c as parameters
- data-imports DatasetJobStorage uses spy, mock
- uv lib uses stubs