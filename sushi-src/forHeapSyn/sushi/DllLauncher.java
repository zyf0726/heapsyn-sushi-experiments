package forHeapSyn.sushi;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class DllLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sushi/dll/LinkedList");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sushi/dll/LinkedList.*");
		super.configureSushiScope(p, "sushi/dll/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sushi/dll.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("sushi.dll.LinkedList");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name ->
				(!name.startsWith("_") || name.equals("_owner"))
				&& !name.equals("modCount")
				&& !name.equals("expectedModCount"));
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "sushi/dll/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("sushi/dll.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		DllLauncher launcher = new DllLauncher();
		launcher.startSushi("sushi.Dll.txt");
	}

}
