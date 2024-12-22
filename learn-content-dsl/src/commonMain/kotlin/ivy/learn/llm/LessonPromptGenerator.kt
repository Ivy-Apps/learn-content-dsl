package ivy.learn.llm

fun main() {
  val prompt = lessonPrompt(
    topic = "Time Complexity (Big O notation)",
    learningGoal = """
Understand what is Big O and be able to differentiate between O(1), O(n), O(logn), O(n^2), and O(nlogn).
   """.trimIndent(),
    structure = listOf(
      "Introduction",
      "Why Time Complexity Matters",
      "Big O Basics",
      "Detailed Breakdown of Key Complexities",
      "Interactive Exercises for Differentiating Complexities",
      "Real-World Applications of Big O",
      "Reflection and Problem-Solving"
    ),
    priorKnowledge = emptyList(),
  )
  println("===== PROMPT ====")
  println(prompt)
  println("=====")
}

fun lessonPrompt(
  topic: String,
  learningGoal: String,
  structure: List<String>,
  priorKnowledge: List<String>,
): String {
  return """
Create an interactive, beginner-friendly lesson to help learners understand $topic. 
The lesson should be:
1. Easy to follow: Use simple, clear language with short sections.
2. Highly visual and example-driven: Include code snippets, diagrams, and real-world analogies to explain concepts.
3. Engaging: Incorporate questions, activities, and scenarios learners can interact with, emphasizing key concepts.
4. Adaptable: The structure and DSL elements should be purposefully selected to achieve the learning goal.

# Structure:
The following structure is a guideline. Feel free to adapt and reorder elements as needed to best serve the learning goals:
${structure.mapIndexed { index, bullet -> "${index + 1}. $bullet" }.joinToString(separator = "\n")}

**Tone**: Friendly, encouraging, and engaging, aimed at learners new to algorithms or computer science.

**Learning Goal**: Ensure learners can "$learningGoal" with confidence.

Use only the Kotlin DSL defined below—no extra structures or deviations.
Ensure that the ids in the lesson are unique and that there are NO duplicated ids.

The lesson should feel coherent, logically connected, and accessible to learners with no prior knowledge besides: "${
    priorKnowledge.joinToString(
      separator = ","
    )
  }."

### Example usage of DSL
These examples are NOT prescriptive. Use the DSL flexibly and creatively, choosing the most suitable elements and order to achieve the learning goals.

```kotlin
lessonContent {
  // A creative example deviating from strict order
  text("introduction") {
    text = "Understanding Big O Notation"
    style = TextStyle.Heading
  }

  codeExample("code_snippet") {
    text = codeBuilder {
      line("# Let's analyze the time complexity of this function")
      line("def search(array, target):")
      line("  for i in range(len(array)):")
      line("    if array[i] == target:")
      line("      return i")
      line("  return -1")
    }
  }

  question("real_world_q") {
    question = "Which complexity best describes searching for a name in a phonebook?"
    clarification = "Think about the strategy used for searching."
    answer(text = "O(1)", explanation = "Incorrect, this represents constant time complexity.")
    answer(text = "O(logn)", correct = true, explanation = "Correct! Binary search in a sorted phonebook is logarithmic.")
    answer(text = "O(n)", explanation = "Not quite; this would be for unsorted linear search.")
  }

  image("diagram") {
    imageUrl = "https://example.com/bigo_chart.png"
  }

  text("summary") {
    text = textBuilder { 
      bullet("Big O helps measure algorithm efficiency.")
      bullet("Common complexities include O(1), O(n), O(logn), O(n^2).")
      bullet("Understand when to use each to optimize performance.")
    }
  }
}
```

# DSL Reference
```kotlin
interface LessonContentScope {
    @LearnCmsDsl
    fun text(id: String, next: String? = null, builder: TextScope.() -> Unit)

    @LearnCmsDsl
    fun question(id: String, builder: QuestionScope.() -> Unit)

    @LearnCmsDsl
    fun image(id: String, builder: ImageScope.() -> Unit)
}

interface TextScope {
    var style: TextStyle
    var text: String
}

enum class TextStyle {
    Heading,
    Body,
    BodySpacingMedium,
    BodySpacingLarge
}

interface QuestionScope {
    var question: String
    var clarification: String?

    @LearnCmsDsl
    fun answer(
        text: String,
        explanation: String? = null,
        correct: Boolean = false
    )
}

@TextBuilderDsl
fun textBuilder(builder: TextBuilderScope.() -> Unit): String {
    val scope = TextBuilder().apply(builder)
    return scope.build()
}

interface TextBuilderScope {
    @TextBuilderDsl
    fun line(text: String)

    @TextBuilderDsl
    fun bullet(text: String)

    @TextBuilderDsl
    fun newLine()
}

fun codeBuilder(builder: CodeBuilderScope.() -> Unit): String {
    val scope = CodeBuilder().apply(builder)
    return scope.build()
}

interface CodeBuilderScope {
    @CodeBuilderDsl
    fun line(text: String)
}
```
""".trimIndent()
}
