package common;

import sushi.Level;
import sushi.Main;
import sushi.exceptions.UnhandledInternalException;
import sushi.execution.evosuite.HeapSynParameters;
import sushi.execution.evosuite.RunHeapSyn;
import sushi.execution.jbse.StateTransSpec;

import static common.Settings.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import heapsyn.common.settings.JBSEParameters;

public class BasicLauncher {
	
	private static final int EVOSUITE_BUDGET	= 180;		// 3min
	private static final int GLOBAL_BUDGET		= 3600;		// 1hr 
	private static final int BUILD_BUDGET		= 1800;		// 30min
	
	private static final int JBSE_BUDGET		= 900;		// 15min
	private static final int MINIMIZER_BUDGET	= 60;		// 1min
	
	private static final int REDUNDANCE			= 1; 
	private static final int PARALLELISM		= 1;
	private static final int NUM_MOSA_TARGETS	= 1;
	
	private static final int MAX_SEQ_LENGTH			= 20;
	private static final int DEFAULT_SCOPE_JBSE		= 5;
	private static final int DEFAULT_SCOPE_HEAPSYN	= 6;
	
	private static Map<String, Integer> scope$JBSE = new HashMap<>();
	private static Map<String, Integer> scope$HeapSyn = new HashMap<>();
	private static Map<String, Integer> countScope = new HashMap<>();
	private static Map<String, Integer> depthScope = new HashMap<>();
	
	static {
		/* ========================= sushi ===========================
		 * (1) AvlTree: scope$AvlTree = 1, scope$AvlNode = 5(6)
		 * (2) NodeCachingLinkedList: scope$List = 1, scope$Node = 5(6)
		 * (3) TreeMap: scope$TreeMap = 1, scope$Entry = 5(6)
		 * (4) LinkedList: scope$List = 1, scope$Iter = 0, scope$DesIter = 0, scope$Entry = 5(6)
		 */		
		
		// sushi.avl
		scope$JBSE.put("sushi/avl/AvlNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("sushi/avl/AvlNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("sushi/avl/AvlTree", 1);
		// sushi.ncll
		scope$JBSE.put("sushi/ncll/NodeCachingLinkedList$LinkedListNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("sushi/ncll/NodeCachingLinkedList$LinkedListNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("sushi/ncll/NodeCachingLinkedList", 1);
		// sushi.treemap
		scope$JBSE.put("sushi/treemap/TreeMap$Entry", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("sushi/treemap/TreeMap$Entry", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("sushi/treemap/TreeMap", 1);
		// sushi.dll
		scope$JBSE.put("sushi/dll/LinkedList$Entry", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("sushi/dll/LinkedList$Entry", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("sushi/dll/LinkedList", 1);
		scope$HeapSyn.put("sushi/dll/LinkedList$ListItr", 0);
		scope$HeapSyn.put("sushi/dll/LinkedList$DescendingIterator", 0);
		
		/* ========================== kiasan ==========================
		 * (1) AATree: scope$AATree = 1, scoe$AANode = 5(6)
		 * (2) BinTree: scope$BinTree = 1, scope$BinNode = 5(6)
		 * (3) LeftistHeap: scope$Heap = 2, scope$Node = 5(6)
		 * (4) StackLi: scope$Stack = 1, scope$Node = 5(6)
		 * (5) AvlTree: scope$AvlTree = 1, scope$AvlNode = 5(6)
		 * (6) TreeMap: scope$TreeMap = 1, scope$Entry = 5(6)
		 */
		
		// kiasan.aatree
		scope$JBSE.put("kiasan/aatree/AATree$AANode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("kiasan/aatree/AATree$AANode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("kiasan/aatree/AATree", 1);
		// kiasan.binsearchtree
		scope$JBSE.put("kiasan/binsearchtree/BinaryNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("kiasan/binsearchtree/BinaryNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("kiasan/binsearchtree/BinarySearchTree", 1);
		// kiasan.leftistheap
		scope$JBSE.put("kiasan/leftistheap/LeftistHeap$LeftistNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("kiasan/leftistheap/LeftistHeap$LeftistNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("kiasan/leftistheap/LeftistHeap", 2);
		// kiasan.stack
		scope$JBSE.put("kiasan/stack/ListNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("kiasan/stack/ListNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("kiasan/stack/StackLi", 1);
		// kiasan.avltree
		scope$JBSE.put("kiasan/avltree/AvlNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("kiasan/avltree/AvlNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("kiasan/avltree/AvlTree", 1);
		// kiasan.redblacktree
		scope$JBSE.put("kiasan/redblacktree/TreeMap$Entry", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("kiasan/redblacktree/TreeMap$Entry", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("kiasan/redblacktree/TreeMap", 1);
		
		/* =========================== SIR ============================
		 * (1) DoubleLinkedList: scope$List = 1, scope$Iter = 0, scope$Entry = 5(6), maxDepth = 20
		 * (2) MyLinkedList: scope$List = 1, scope$Iter = 1, scope$Node = 5(6)
		 */
		
		// sir.dll
		scope$JBSE.put("sir/dll/DoubleLinkedList$Entry", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("sir/dll/DoubleLinkedList$Entry", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("sir/dll/DoubleLinkedList", 1);
		scope$HeapSyn.put("sir/dll/DoubleLinkedList$ListItr", 0);
		depthScope.put("sir/dll/", 50);
		// sir.sll
		scope$JBSE.put("sir/sll/MyLinkedList$MyListNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("sir/sll/MyLinkedList$MyListNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("sir/sll/MyLinkedList", 1);
		scope$HeapSyn.put("sir/sll/MyLinkedList$MyLinkedListItr", 1);
		
		/* ========================= JavaScan =========================
		 * (1) BinomialHeap: scope$Heap = 1, scope$Node = 5(6)
		 * (2) SkewHeap: scope$Heap = 1, scope$Node = 5(6)
		 */
		
		// javascan.binomial
		scope$JBSE.put("javascan/binomial/BinomialHeap$BinomialHeapNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("javascan/binomial/BinomialHeap$BinomialHeapNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("javascan/binomial/BinomialHeap", 1);
		// javascan.skewheap
		scope$JBSE.put("javascan/skewheap/SkewHeap$SkewNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("javascan/skewheap/SkewHeap$SkewNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("javascan/skewheap/SkewHeap", 1);
		
		/* =========================== verify =========================
		 * (1) AATree: scope$AATree = 1, scoe$AANode = 5(6)
		 * (2) BinTree: scope$BinTree = 1, scope$BinNode = 5(6)
		 * (3) LeftistHeap: scope$Heap = 2, scope$Node = 5(6)
		 * (4) AvlTree: scope$AvlTree = 1, scope$AvlNode = 5(6)
		 * (5) TreeMap: scope$TreeMap = 1, scope$Entry = 5(6)
		 */
		
		// verify.aatree
		scope$JBSE.put("verify/aatree/AATree$AANode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("verify/aatree/AATree$AANode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("verify/aatree/AATree", 1);
		// verify.binsearchtree
		scope$JBSE.put("verify/binsearchtree/BinaryNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("verify/binsearchtree/BinaryNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("verify/binsearchtree/BinarySearchTree", 1);
		// verify.leftistheap
		scope$JBSE.put("verify/leftistheap/LeftistHeap$LeftistNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("verify/leftistheap/LeftistHeap$LeftistNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("verify/leftistheap/LeftistHeap", 2);
		// verify.avltree
		scope$JBSE.put("verify/avltree/AvlNode", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("verify/avltree/AvlNode", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("verify/avltree/AvlTree", 1);
		// verify.redblacktree
		scope$JBSE.put("verify/redblacktree/TreeMap$Entry", DEFAULT_SCOPE_JBSE);
		scope$HeapSyn.put("verify/redblacktree/TreeMap$Entry", DEFAULT_SCOPE_HEAPSYN);
		scope$HeapSyn.put("verify/redblacktree/TreeMap", 1);
		
	}
	
	public void configureSushi(sushi.Options p) {
		// Local configurations
		p.setJava8Path(JAVA8_HOME);
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setEvosuiteNoDependency(true);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setJBSELibraryPath(JBSE_PATH);
		p.setZ3Path(Z3_PATH);
		
		// Target
		p.setClassesPath(BIN_PATH_FOR_SUSHI);
		
		// Analysis params
		p.setEvosuiteBudget(EVOSUITE_BUDGET);
		p.setJBSEBudget(JBSE_BUDGET);
		p.setMinimizerBudget(MINIMIZER_BUDGET);
		
		// Tmp out directories
		p.setOutDirPath(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);
		
		// Redundance and parallelism
		p.setRedundanceEvosuite(REDUNDANCE);
		p.setParallelismEvosuite(PARALLELISM);
		p.setParallelismJBSE(PARALLELISM);
		p.setNumMOSATargets(NUM_MOSA_TARGETS);
		
		// Evosuite
		p.setAdditionalEvosuiteArgs("-Dobject_reuse_probability=0.8");
		
		// Logging
		p.setLogLevel(Level.DEBUG);
		
		// Timeout
		p.setGlobalBudget(GLOBAL_BUDGET);
	}
	
	final protected void configureSushiScope(sushi.Options p, String packageName) {
		for (Entry<String, Integer> entry : scope$JBSE.entrySet()) {
			String clsName = entry.getKey();
			int scope = entry.getValue();
			if (clsName.startsWith(packageName)) {
				p.setHeapScope(clsName, scope);
				System.out.print("INFO - experimental setup: set heap scope (sushi.jbse) of class ");
				System.out.println(clsName + " to " + scope);
			}
		}
		if (depthScope.containsKey(packageName)) {
			int scope = depthScope.get(packageName);
			p.setDepthScope(scope);
			System.out.print("INFO - experimental setup: set depth scope (sushi.jbse) for package ");
			System.out.println(packageName + " to " + scope);
		}
		if (countScope.containsKey(packageName)) {
			int scope = countScope.get(packageName);
			p.setCountScope(scope);
			System.out.print("INFO - experimental setup: set count scope (sushi.jbse) for package ");
			System.out.println(packageName + " to " + scope);
		}
	}
	
	final private void configureHeapSyn() {
		heapsyn.common.settings.Options o = HeapSynParameters.heapsynOptions;
		heapsyn.common.settings.JBSEParameters p = HeapSynParameters.jbseParams;
		o.setSolverExecPath(Z3_PATH.toAbsolutePath().toString());
		o.setTimeBudget(BUILD_BUDGET);
		p.setJBSEClassPath(JBSE_PATH_FOR_HEAPSYN.toAbsolutePath().toString());
		p.setJBSESourcePath(JBSE_SRC_PATH_FOR_HEAPSYN.toAbsolutePath().toString());
		p.setTargetClassPath(BIN_PATH_FOR_HEAPSYN.toAbsolutePath().toString());
		p.setTargetSourcePath(SRC_PATH_FOR_HEAPSYN.toAbsolutePath().toString());
		p.setShowOnConsole(false);
		heapsyn.common.Logger.setLevelInfo();
	}
	
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		return null;
	}
	
	final protected void configureHeapSynScope(HeapSynParameters p, String packageName) {
		JBSEParameters jbseParams = HeapSynParameters.jbseParams;
		jbseParams.resetAllScope();
		for (Entry<String, Integer> entry : scope$JBSE.entrySet()) {
			String clsName = entry.getKey();
			int scope = entry.getValue();
			if (clsName.startsWith(packageName)) {
				jbseParams.setHeapScope(clsName, scope);
				System.out.print("INFO - experimental setup: set heap scope (heapsyn.jbse) of class ");
				System.out.println(clsName + " to " + scope);
			}
		}
		if (depthScope.containsKey(packageName)) {
			int scope = depthScope.get(packageName);
			jbseParams.setDepthScope(scope);
			System.out.print("INFO - experimental setup: set depth scope (heapsyn.jbse) for package ");
			System.out.println(packageName + " to " + scope);
		}
		if (countScope.containsKey(packageName)) {
			int scope = countScope.get(packageName);
			jbseParams.setCountScope(scope);
			System.out.print("INFO - experimental setup: set count scope (heapsyn.jbse) for package ");
			System.out.println(packageName + " to " + scope);
		}
		for (Entry<String, Integer> entry : scope$HeapSyn.entrySet()) {
			String clsName = entry.getKey();
			int scope = entry.getValue();
			if (clsName.startsWith(packageName)) {
				final Class<?> cls;
				try {
					cls = Class.forName(clsName.replace('/', '.'));
				} catch (ClassNotFoundException e) {
					throw new UnhandledInternalException(e);
				}
				p.setHeapScope(cls, scope);
				System.out.print("INFO - experimental setup: set heap scope (heapsyn) of class ");
				System.out.println(clsName + " to " + scope);
			}
		}
		p.setMaxSeqLength(MAX_SEQ_LENGTH);
		System.out.print("INFO - experimental setup: set max sequence length for package ");
		System.out.println(packageName + " to " + MAX_SEQ_LENGTH);
	}
	
	final protected void configureHeapSynHEXFile(Path hexFilePath) {
		if (hexFilePath != null) {
			HeapSynParameters.jbseParams.setSettingsPath(hexFilePath.toAbsolutePath().toString());
		}
	}
	
	final public void startSushi() {
		final int maxRestartTimes = 2;
		int exitCode = __startSushi(maxRestartTimes);
		if (exitCode != 0) {
			System.out.println("ERROR - exit with code " + exitCode);
			// System.exit(exitCode);
		}
	}
	
	final private int __startSushi(int maxRestartTimes) {
		final sushi.Options o = new sushi.Options();
		this.configureSushi(o);
		try {
			HeapSynParameters params = this.getHeapSynParameters();
			if (params != null) {
				configureHeapSyn();
				StateTransSpec.setFieldFilter(params.getFieldFilter());
				o.setHeapSynRunner(new RunHeapSyn(params));
			} else {
				StateTransSpec.setFieldFilter(name -> true);
				o.setHeapSynRunner(null);
			}
		} catch (ClassNotFoundException e) {
			throw new UnhandledInternalException(e);
		}
		final Main m = new Main(o);
		final int exitCode = m.start();
		if (exitCode != 0 && maxRestartTimes > 0) {
			System.out.println("ERROR - exit with code " + exitCode + ", restart Sushi");
			return __startSushi(maxRestartTimes - 1);
		} else {
			return exitCode;
		}
	}

}
