package compileExperiments

import Command
import java.nio.file.Path

interface CompileExperiment {
    fun generateCode(n: Int): String
    val description: String
    val basePath: Path
    val executionCommand: Command
    val compiledFilePath: Path
}