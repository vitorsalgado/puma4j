# puma4j-kotlin
Kotlin delegate to load resources.

## Usage
```
  private val data: String by res("hello-world.txt")
  private val simpleModel: SimpleModel by res("simple.json")
  private val complexModel: List<ComplexModel> by res("complex.json")
```
