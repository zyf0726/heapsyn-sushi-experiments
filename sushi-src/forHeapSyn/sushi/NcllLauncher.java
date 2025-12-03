package forHeapSyn.sushi;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.execution.evosuite.HeapSynParameters;

public class NcllLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sushi/ncll/NodeCachingLinkedList");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sushi/ncll/NodeCachingLinkedList.*");
		super.configureSushiScope(p, "sushi/ncll/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sushi/ncll.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName("sushi.ncll.NodeCachingLinkedList");
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name ->
				(!name.startsWith("_") || name.equals("_owner"))
				&& !name.equals("modCount"));
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, "sushi/ncll/");
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("sushi/ncll.jbse"));
		return p;
	}

	public static void main(String[] args) {
		NcllLauncher launcher = new NcllLauncher();
		launcher.startSushi("sushi.CachingList.txt");
	}
}
