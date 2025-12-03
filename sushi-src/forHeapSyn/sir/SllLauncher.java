package forHeapSyn.sir;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class SllLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sir/sll/MyLinkedList");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sir/sll/MyLinkedList.*");
		super.configureSushiScope(p, "sir/sll/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sir/sir-sll.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("sir.sll.MyLinkedList");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "sir/sll/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("sir/sir-sll.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		SllLauncher launcher = new SllLauncher();
		launcher.startSushi("sir.sll.txt");
	}

}
