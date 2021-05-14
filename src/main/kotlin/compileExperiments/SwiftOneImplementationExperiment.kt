package compileExperiments

import Environment
import org.intellij.lang.annotations.Language

class SwiftOneImplementationExperiment(environment: Environment) : SwiftCompileExperiment(environment) {
    override val description: String get() = "Swift +- no overloads"

    @Language("swift")
    override fun generateCode(n: Int) = """
        infix operator +-: AdditionPrecedence

        struct A {}
        func +-(a: A, _: A) -> A {a}
        func ab() -> A {A()}

        let __: A = ab()${" +- ab()".repeat(n)}
    """.trimIndent()
}