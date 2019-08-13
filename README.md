# record-hacks
`record-hacks` is a Java library that will help you track "creative solutions" in your code. You can use it to document the rationale
behind some questionable choices in your code as well an aspirational date for an intended fix.
 
This library provides:
1. Annotations [`@Hack`](https://github.com/rohitkumbhar/record-hacks/blob/master/annotations/src/main/java/net/kumbhar/dx/Hack.java) and [`@Hacks`](https://github.com/rohitkumbhar/record-hacks/blob/master/annotations/src/main/java/net/kumbhar/dx/Hacks.java)
1. A compiler processor that will print the hacks in your code as warnings and fail compilation 
if a hack is past its `fixByDate`

These annotations are will be discarded by the compiler after processing and will not be carried over to any class files.

## Usage

### Import the library
Maven repo setup in progress...

### In your code
```java
    @Hack(
        trackedBy = "https://example.com/PROJECT-1234",
        fixBeforeDate = "2019-12-31", /* Compilation will fail if this annotation is not removed after this date */
        description = "This is a hack that will make my life miserable soon"
    )
    public void hackyMethod() {
        // Hack implementation
    }
```
The annotation can be applied to fields, methods, method parameters, types, local variables and constructors. 

### Sample output from the [test](https://github.com/rohitkumbhar/record-hacks/blob/master/tests/src/main/java/net/kumbhar/dx/HacksTest.java)
```
/workspace/record-hacks/tests/src/main/java/net/kumbhar/dx/HacksTest.java:8: warning: Hack: This should really have the correct type but we dont know what it is.
    private Object fieldUsage;
                   ^
/workspace/record-hacks/tests/src/main/java/net/kumbhar/dx/HacksTest.java:18: error: Hack: This hack needed to be fixed yesterday
    public void buildFailureUsage() {
                ^
/workspace/record-hacks/tests/src/main/java/net/kumbhar/dx/HacksTest.java:22: warning: Hack: This should be its own type
    public void methodParameterUsage(@Hack(description = "This should be its own type") Map<String, String> data) {
                                                                                                            ^
/workspace/record-hacks/tests/src/main/java/net/kumbhar/dx/HacksTest.java:13: warning: Hack: This is a hack that will make my life miserable in 1 month
    public void repeatedUsage() {
                ^
/workspace/record-hacks/tests/src/main/java/net/kumbhar/dx/HacksTest.java:13: warning: Hack: This is a hack that will make my life miserable in 2 months
    public void repeatedUsage() {
                ^
1 error
5 warnings

```



