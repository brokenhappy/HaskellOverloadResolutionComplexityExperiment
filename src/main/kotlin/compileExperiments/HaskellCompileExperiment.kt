package compileExperiments

import Command
import Environment
import org.intellij.lang.annotations.Language
import java.nio.file.Path

class HaskellCompileExperiment(private val environment: Environment) : CompileExperiment {
    override val description get() = "Haskell"

    @Language("Haskell")
    override fun generateCode(n: Int) = """
        {-# LANGUAGE FlexibleInstances #-}
        {-# LANGUAGE MultiParamTypeClasses #-}
        {-# LANGUAGE FunctionalDependencies #-}
        
        module Main
          ( main,
          )
        where
        
        newtype A = A { __ :: String }
        newtype B = B { ___ :: String }
        
        class AB a where
          ab :: a
          (+-) :: a -> a -> a
        
        instance AB A where
          ab = A "a"
          (+-) lhs rhs = A ${'$'} __ lhs ++ __ rhs
        
        instance AB B where
          ab = B "b"
          (+-) lhs rhs = B ${'$'} ___ lhs ++ ___ rhs
        
        ____ :: A
        ____ = ab${" +- ab".repeat(n)}
        
        main :: IO ()
        main = do
          putStrLn ${'$'} __ ____
    """.trimIndent()

    override val basePath: Path get() = Path.of("${environment.baseProjectFolder}/${environment.haskellProjectName}")
    override val executionCommand: Command get() = Command("/usr/local/bin/stack build --exec ${environment.haskellProjectName}-exe")
    override val compiledFilePath: Path get() = Path.of("app/Main.hs")
}