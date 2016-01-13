package asm.visitorApi;


@FunctionalInterface
public interface IVisitMethod {
	public void execute(ITraverser t);
}
