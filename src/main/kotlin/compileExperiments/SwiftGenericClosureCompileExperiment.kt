package compileExperiments

import Environment
import org.intellij.lang.annotations.Language

class SwiftGenericClosureCompileExperiment(environment: Environment) : SwiftCompileExperiment(environment) {
    override val description: String get() = "Swift nested closures with generic ++ overloads"

    @Language("swift")
    override fun generateCode(n: Int): String = """
        infix operator ++: AdditionPrecedence
        
        class A {}
        class B {}

        func ++ <E>(lhs: E, rhs: E) -> E where E: B {lhs}
        func ++ <E>(lhs: E, rhs: E) -> E where E: A {rhs}
        
        func foo(_:(B) -> A) -> A {A()}
        func foo(_:(B) -> B) -> B {B()}
        
        let _ = ${(1..n).fold("foo { a0 in a0") { acc, i -> "foo { a$i in $acc ++ a$i" } + " }".repeat(n)} }
    """.trimIndent()
//        let _ = foo { x in ${(0..n).fold("x") { acc, _ -> "foo { _ in $acc }" } } }
//        let _ = ${(0 until n).joinToString(" + ") { listOf("-1", "+1", "-(1)", "+(1)", "1").random() }}
}
