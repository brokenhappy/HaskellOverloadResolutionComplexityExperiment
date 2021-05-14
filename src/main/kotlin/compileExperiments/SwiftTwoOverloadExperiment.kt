package compileExperiments

import Environment
import org.intellij.lang.annotations.Language

class SwiftTwoOverloadExperiment(environment: Environment) : SwiftCompileExperiment(environment) {
    override val description: String get() = "Swift +- overloaded"

    @Language("swift")
    override fun generateCode(n: Int) = """
        infix operator +-: AdditionPrecedence

        struct A {}
        struct B {}
        func +-(a: A, _: A) -> A {a}
        func +-(b: B, _: B) -> B {b}

        func ab() -> A {A()}
        func ab() -> B {B()}
        let __: A = ab()${" +- ab()".repeat(n)}
    """.trimIndent()
}