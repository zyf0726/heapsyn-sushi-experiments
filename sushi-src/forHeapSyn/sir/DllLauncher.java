package forHeapSyn.sir;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class DllLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sir/dll/DoubleLinkedList");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sir/dll/DoubleLinkedList.*");
		super.configureSushiScope(p, "sir/dll/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sir/sir-dll.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("sir.dll.DoubleLinkedList");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "sir/dll/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("sir/sir-dll.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		DllLauncher launcher = new DllLauncher();
		launcher.startSushi();
	}

}
