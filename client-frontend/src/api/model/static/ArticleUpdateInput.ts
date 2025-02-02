export interface ArticleUpdateInput {
    readonly articleId: number;
    readonly authorId: number;
    readonly category?: string | undefined;
    readonly content: string;
    readonly copyrightInfo?: string | undefined;
    readonly title: string;
}
