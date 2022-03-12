package forHeapSyn.verify;

import static common.Settings.SETTINGS_PATH;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;
import sushi.execution.evosuite.HeapSynParameters;

public class BSTVerifier extends BasicLauncher {
	
	private final String METHOD_NAME;
	
	public BSTVerifier(String methodName) {
		System.out.println("INFO - start to verify " + methodName + " of class " + METHOD_CLASS);
		this.METHOD_NAME = methodName;
	}
	
	private static final String PACKAGE				= "verify/binsearchtree/";
	private static final String METHOD_CLASS		= PACKAGE + "BinarySearchTree";
	private static final String METHOD_DESCRIPTOR	= "()V";
	
	@Override
	public void configureSushi(Options p) {
		super.configureSushi(p);
		p.setTargetMethod(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
		p.setCoverage(Coverage.UNSAFE);
		super.configureSushiScope(p, PACKAGE);
		p.setHEXFiles(SETTINGS_PATH.resolve("verify/bst.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName(METHOD_CLASS.replace('/', '.'));
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, PACKAGE);
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("verify/bst.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		new BSTVerifier("check$ordered").startSushi();
	}

}
