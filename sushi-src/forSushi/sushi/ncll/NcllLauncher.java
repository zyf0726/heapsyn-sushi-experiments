package forSushi.sushi.ncll;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class NcllLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/sushi/ncll/NodeCachingLinkedList");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("forSushi/sushi/ncll/NodeCachingLinkedList.*");
		super.configureScope(p, "forSushi/sushi/ncll/NodeCachingLinkedList$LinkedListNode");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/sushi/ncll.jbse"));
	}

	public static void main(String[] args) {
		NcllLauncher launcher = new NcllLauncher();
		launcher.startSushi();
	}
}
