import compileExperiments.CompileExperiment
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Path

class CompileExperimentPerformerTest {
    @Test
    @Tag("SlowTest")
    fun `test if bash with only 200ms sleep command is executed, the execution time should be about the same`() {
        Assertions.assertEquals(
            225.0,
            CompileExperimentPerformer()
                .measureExecutionTimeMillisFor(object : CompileExperiment {
                    @Language("bash")
                    override fun generateCode(n: Int) = "sleep 0.2"
                    override val description: String get() = "Test experiment"
                    override val basePath = File(
                        CompileExperimentPerformerTest::class.java.getResource("testBash.sh")?.path
                            ?: throw IllegalStateException("Resources folder should contain testBash.sh file in root")
                    ).parentFile.toPath()
                    override val executionCommand: Command get() = Command("bash testBash.sh")
                    override val compiledFilePath get() = Path.of("testBash.sh")
                }, 0)
                .toDouble(),
            25.0,
        )
    }
}