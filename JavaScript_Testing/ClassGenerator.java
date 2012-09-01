

//Javassist jar is needed to run this code.
import java.lang.reflect.Method;
import java.util.ArrayList;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;

public class ClassGenerator {
	
	//Given a class Name and a list of method names,it generates
	//a class that contain generated methods with a dummy stub.
	public Class<?> getClass(String className, ArrayList<String> methods)
	{
		try 
		{
			ClassPool pool = ClassPool.getDefault();
            // create the class 
            CtClass class_ = pool.makeClass(className);
            // create the method
			String methodStub = "";
			for (String methodName : methods)
			{
				methodStub = "public void " +methodName+ "() {};";
				CtMethod method = CtNewMethod.make(methodStub, class_);
				class_.addMethod(method);
				ClassFile classFile = class_.getClassFile();
				ConstPool constPool = classFile.getConstPool();

				// create the annotation
				AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
				Annotation annotation = new Annotation("org.junit.Test", constPool);
				attr.addAnnotation(annotation);
			
				//add the annotation to the method
				method.getMethodInfo().addAttribute(attr);
			}
            Class<?> generatedClass = class_.toClass();
            return generatedClass;

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
}
	
    public static void main(String[] args)
    {
    	ClassGenerator test = new ClassGenerator();
    	ArrayList<String> names = new ArrayList<String>();
    	names.add("method1");
    	names.add("method2");
    	Class<?> class_ = test.getClass("nalla",names);
    	System.out.println("class name: " + class_.getName());
    	Method[] methods = class_.getDeclaredMethods();
    	System.out.println("method names: " + methods.length);
    	
    }
    
}
