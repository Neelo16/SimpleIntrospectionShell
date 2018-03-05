# SimpleIntrospectionShell
A shell in Java used to access a bunch of classes.

This is my solution to an exercise for the **Advanced Programming** course at **Instituto Superior Técnico**. This is
the task statement:

## Task

Develop a program that provides introspection-based functionalities for Java classes. The interaction with the user should be provided in a shell that takes as input commands and prints as output the result returned by the execution of the command.

The commands to support are:

  -  Class \<*name*\> : obtain instance of Class \<*name*\>.
  -  Set \<*name*\> : save object from last result with name \<*name*\>.
  -  Get \<*name*\> : select object previously saved with \<*name*\>.
  -  Index \<*int*\> : select object within an array. The array should have been obtained as the result of the previous command.
  -  Package [*information_type*]: obtain package information of the class of the object previously saved. *information_type* is an optional parameter that might have the values "Implementation", "Annotation", "Specification" or "Name". If *information_type* is not specified, then all the available information about the class of the object should be listed.

Whenever the user enters an unknown command, instead of immediately giving an error message, the shell should first check if the attempted command names a method that can be invoked on the result of the previous command. In this case the following parameters given in the shell can be used as the actual parameters of the method to invoke. An attempt is made to discover the best method via reflection and to convert the given parameters to the correct types. (Suggestion: start simple, with support only for primitive types).

Consider the following interaction as an example:

<pre>
java -cp classes pt.ist.ap.labs.Shell
<b>Command:></b> Class java.lang.String
class java.lang.String
<b>Command:></b> Set s
Saved name for object of type: class java.lang.Class
class java.lang.String
<b>Command:></b> Package
Package information (of  [class java.lang.Class] of previous object [class java.lang.String]): package java.lang
toString: package java.lang
hashCode: -888658374 
getName: java.lang 
getAnnotations: 
getDeclaredAnnotations: 
isSealed: true 
getSpecificationTitle: Result Non Defined
getSpecificationVersion: Result Non Defined
getSpecificationVendor: Result Non Defined
getImplementationTitle: Result Non Defined
getImplementationVersion: Result Non Defined
getImplementationVendor: Result Non Defined
class java.lang.String
<b>Command:></b> getDeclaredConstructors
Trying generic command: getDeclaredConstructors 
public java.lang.String(byte[])
public java.lang.String(byte[],int,int)
public java.lang.String(byte[],java.nio.charset.Charset)
public java.lang.String(byte[],java.lang.String) throws java.io.UnsupportedEncodingException
public java.lang.String(byte[],int,int,java.nio.charset.Charset)
java.lang.String(char[],int,int,java.lang.Void)
java.lang.String(java.lang.AbstractStringBuilder,java.lang.Void)
java.lang.String(char[],boolean)
public java.lang.String(java.lang.StringBuilder)
public java.lang.String(java.lang.StringBuffer)
java.lang.String(byte[],byte)
public java.lang.String(char[],int,int)
public java.lang.String(char[])
public java.lang.String(java.lang.String)
public java.lang.String()
public java.lang.String(byte[],int,int,java.lang.String) throws java.io.UnsupportedEncodingException
public java.lang.String(byte[],int)
public java.lang.String(byte[],int,int,int)
public java.lang.String(int[],int,int)
<b>Command:></b> Index 12
public java.lang.String(char[])
<b>Command:></b> Package Name
Package information (of the [class java.lang.reflect.Constructor] of object [public java.lang.String(char[])]): package java.lang.reflect
getName: java.lang.reflect 
public java.lang.String(char[])
<b>Command:></b> Set c
Saved name for object of type: class java.lang.reflect.Constructor
public java.lang.String(java.lang.String)
<b>Command:></b> Get s
class java.lang.String
<b>Command:></b> getPackage
Trying generic command: getPackage 
package java.lang
<b>Command:></b> Exit
Goodbye!
</pre>
