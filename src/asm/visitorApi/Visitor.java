package asm.visitorApi;

import java.util.HashMap;
import java.util.Map;

public class Visitor implements IVisitor {
	Map<LookupKey, IVisitMethod> keyToVisitMethodMap;
	
	public Visitor() {
		this.keyToVisitMethodMap = new HashMap<>();
	}

	@Override
	public void preVisit(ITraverser t) {
		this.doVisit(VisitType.PreVisit, t);
	}

	@Override
	public void nameVisit(ITraverser t) {
		this.doVisit(VisitType.NameVisit, t);
	}

	@Override
	public void fieldVisit(ITraverser t) {
		this.doVisit(VisitType.FieldVisit, t);
	}

	@Override
	public void methodVisit(ITraverser t) {
		this.doVisit(VisitType.MethodVisit, t);
	}

	@Override
	public void postVisit(ITraverser t) {
		this.doVisit(VisitType.PostVisit, t);
	}
	
	private void doVisit(VisitType vType, ITraverser t) {
		LookupKey key = new LookupKey(vType, t.getClass());
		System.out.println(t.getClass());
		IVisitMethod m = this.keyToVisitMethodMap.get(key);
		if(m != null)
			m.execute(t);
	}

	@Override
	public void addVisit(VisitType visitType, Class<?> clazz, IVisitMethod m) {
		LookupKey key = new LookupKey(visitType, clazz);
		this.keyToVisitMethodMap.put(key, m);
	}

	@Override
	public void removeVisit(VisitType visitType, Class<?> clazz) {
		LookupKey key = new LookupKey(visitType, clazz);
		this.keyToVisitMethodMap.remove(key);
	}
}