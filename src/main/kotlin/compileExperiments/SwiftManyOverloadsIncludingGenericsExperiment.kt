package compileExperiments

import Environment
import org.intellij.lang.annotations.Language

class SwiftManyOverloadsIncludingGenericsExperiment(environment: Environment) : SwiftCompileExperiment(environment) {
    override val description: String get() = "Swift +"

    @Language("swift")
    override fun generateCode(n: Int) = """
        struct A {}
        struct B {}
        func +(lhs: A, rhs: A) -> A {a}
        func +(lhs: B, rhs: B) -> B {b}

        func ab() -> A {A()}
        func ab() -> B {B()}
        let __: A = ab()${" + ab()".repeat(n)}
    """.trimIndent()
}