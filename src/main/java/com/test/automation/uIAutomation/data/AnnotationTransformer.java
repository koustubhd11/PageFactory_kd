package com.test.automation.uIAutomation.data;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.test.automation.uIAutomation.testBase.SetupEnvironment;

import java.lang.reflect.*;
import java.util.ArrayList;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @Description : This method will be invoked by TestNG to give you a chance to modify a TestNG annotation read from your test classes. 
 *    			  You can change the values you need by calling any of the setters on the ITest interface. Note that only one of the 
 *    			  three parameters testClass, testConstructor and testMethod will be non-null.
 * Parameters: annotation - The annotation that was read from your test class.
 * Parameters: testClass - If the annotation was found on a class, this parameter represents this class (null otherwise).
 * Parameters: testConstructor - If the annotation was found on a constructor, this parameter represents this constructor (null otherwise).
 * Parameters: testMethod - If the annotation was found on a method, this parameter represents this method (null otherwise).
 * @author Vaibhav.Shrivastava
 *
 */
public class AnnotationTransformer implements IAnnotationTransformer 
{
	@SuppressWarnings({ "rawtypes"})
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
	{ 
		DOMConfigurator.configure("log4j.xml");
		BasicConfigurator.configure();
		SetupEnvironment test = new SetupEnvironment();
    	ArrayList<String> ae = new ArrayList<String>();
    	try 
    	{
				ae = test.start();
    	}
    	catch (IllegalAccessException e) 
    	{
				e.printStackTrace();
		}
    	catch (IllegalArgumentException e) 
		{
				e.printStackTrace();
		}
    	catch (InvocationTargetException e) 
    	{
				e.printStackTrace();
		}
    	catch (NoSuchMethodException e) 
    	{
				e.printStackTrace();
		}
    	catch (Exception e) 
    	{
				e.printStackTrace();
		}
			
		if (ae.contains(annotation.getTestName())) 
		{
		    annotation.setEnabled(true);
		}
	}
}