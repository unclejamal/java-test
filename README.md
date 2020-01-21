## From Pawel: How to run it
  1. Please run the acceptance tests by executing:
      - shop.AcceptanceTest class from IDE
      - or as a Gradle command: ./gradlew clean test --tests AcceptanceTest
  2. Please run all the tests by executing:
      - src/main/test directory from IDE
      - or as a Gradle command: ./gradlew clean test
  3. Please run the application and use the command line interface by executing:
      - shop.main.Main class from IDE
      - or as a Gradle command: ./gradlew run -q --console=plain
                
## From Pawel: My assumptions
  1. For the purpose of simplicity I picked Double to represent money. In a real application I would have chosen a better suited BigDecimal.
  2. The way I understood the instructions, when you start the application it should always run "now" - meaning that the "Buy 2 tins of soup and get a loaf of bread half price" offer is active and the "Apples have a 10% discount" offer is not. That means the offer for apples cannot be manually tested from the command line interface. It is however tested via AcceptanceTests and unit tests.  
  3. I assumed the "Buy 2 tins of soup and get a loaf of bread half price" offer is applicable multiple times (e.g. twice if the basket contains 4 tins of soup and 2 loaves of bread).

# Java Exercise

This is a simple exercise to allow you to demostrate your software engineering skillset. It's completly up to you how long you give yourself, stop when you're happy with the quality of your work, but we don't expect it to take too long.

## Instructions
  1. Please fork this repository and work on your fork.
     _https://help.github.com/en/articles/fork-a-repo_
  2. Commit changes frequently so that we can review your progression with you later.
     _https://crealytics.com/blog/5-reasons-keeping-git-commits-small/_
  3. You should use a recognised build tool, e.g. _gradle_, _maven_...
  4. We are currently using [open JDK 8u181](https://cdn.azul.com/zulu/bin/zulu8.31.0.1-jdk8.0.181-win_x64.msi). But please use the JDK you think is most appropriate to demonstrate your skillset. 
  5. We don't expect 100% code coverage, we expect well tested code.
     _https://medium.com/@nicklee1/why-test-code-coverage-targets-are-a-bad-idea-1b9b8ef711ef_
  6. Any other tools or libraries used must be freely available. Any third-party jars must be accessible via [maven central](https://mvnrepository.com/repos/central).
  7. The specification is below.
  
## Specification

A local shop, Henry’s Grocery, has asked you to author an IT solution for them to price up a basket of shopping for their customers.

Henry’s Grocery, currently only stocks four items and has two promotions. These are as follows:

### Stock Items
        
|  **product** | **unit**   | **cost** |
| :---  | :---: | :---: |
|  soup    | tin    | 0.65 |
|  bread   | loaf   | 0.80 |
|  milk    | bottle | 1.30 |
|  apples  | single | 0.10 |

### Discounts
 
| **the offer**| **valid from** | **valid to** | 
| :---     | :---: | :---: |    
| Buy 2 tins of soup and get a loaf of bread half price | yesterday | for 7 days |
| Apples have a 10% discount | from 3 days hence | until the end of the following month |

### Inputs
 All basket items added via the command prompt.

### Outputs
All outputs must print to the command line.
     
### Tests
   - Price a basket containing: 3 tins of soup and 2 loaves of bread, bought today, 
     - Expected total cost = 3.15;
   - Price a basket containing: 6 apples and a bottle of milk, bought today, 
     - Expected total cost = 1.90;
   - Price a basket containing: 6 apples and a bottle of milk, bought in 5 days time,
     - Expected total cost = 1.84;
   - Price a basket containing: 3 apples, 2 tins of soup and a loaf of bread, bought in 5 days time,
     - Expected total cost = 1.97.