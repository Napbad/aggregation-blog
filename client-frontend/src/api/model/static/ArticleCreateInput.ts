export interface ArticleCreateInput {
    readonly articleId?: number | undefined;
    readonly authorId: number;
    readonly category?: string | undefined;
    readonly content: string;
    readonly copyrightInfo?: string | undefined;
    readonly title: string;
}
