package forSushi.sushi;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

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

	public static void main(String[] args) {
		NcllLauncher launcher = new NcllLauncher();
		launcher.startSushi();
	}
}
