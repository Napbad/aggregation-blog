export interface CompletableFuture<T> {
    readonly cancelled: boolean;
    readonly done: boolean;
    readonly completedExceptionally: boolean;
    readonly numberOfDependents: number;
}
