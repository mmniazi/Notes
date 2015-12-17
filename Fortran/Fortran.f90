PROGRAM TUTORIAL

! For some reasons it will only accept variable declaration before any other statements
! Every statement should either by a call or an assignment

! USE statement can be used for importing modules (.mod files)
USE REAL_PRECISION

! Here is how you are going to turn off auto type infer. It causes problems as
! a new variable is declared if you write a wrong spelling of variable
IMPLICIT NONE

! Here is initialization syntax
! < type > [,< attribute-list >] :: < variable-list > & [ = < value >]
INTEGER :: i = 5, j = 100
REAL :: x, y = 1.0E5
COMPLEX :: val = (1.0,1.732)
CHARACTER(LEN=5) :: light = 'Amber'
CHARACTER(LEN=9) :: gumboot = 'Wellie'
LOGICAL :: on = .TRUE., off = .FALSE.

! Constants are defined by using PARAMETER keyword
REAL, PARAMETER :: pi = 3.141592

! Ampersand is used to for line continuation
CHARACTER(LEN=*), PARAMETER :: son = 'bart', dad = &
"Homer"

! ** is for power
REAL :: a = 2 ** 8

! Character length can be infered from string if its a constant
CHARACTER(LEN=*), PARAMETER :: str1 = "abcdef"

! Expressions
i = i + 1 ! numeric valued: addition
light = "Ward "//light ! character valued: concatenation
on = on .AND. off ! logical: intersection

! Substrings can be taken
light = str1 ! is “abcdef”
light = str1(1:1) ! is “a” (not str1(1) which is illegal)
light = str1(2:4) ! is “bcd”

END PROGRAM