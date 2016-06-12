{-
    Haskell is a purely functional programming language. It has:
    - Static typing
    - Referential transparency
    - Lazy by default
    - Type inference
-}

{-
    - +, * etc are all infix functions, they are sandwitched between the two parameters
    - Normally functions are prefix functions, that are written before parameters e.g 
-}

succ 8

-- not equals is written as /=
5 /= 4

min 6 7 8 + max 5 19 -- equals to 25

-- Infix functions are executed after prefix functions

-- You can use prefix functions as infix functions by using backticks

6 `min` 7

-- For using succ succ 8 you have to use:
succ(succ 8)