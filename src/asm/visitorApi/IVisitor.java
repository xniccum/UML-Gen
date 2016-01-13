package asm.visitorApi;


public interface IVisitor {
	public void preVisit(ITraverser t);
	public void nameVisit(ITraverser t);
	public void fieldVisit(ITraverser t);
	public void methodVisit(ITraverser t);
	public void postVisit(ITraverser t);
	
	public void addVisit(VisitType visitType, Class<?> clazz, IVisitMethod m);
	public void removeVisit(VisitType visitType, Class<?> clazz);
}
