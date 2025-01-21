export interface AuthorUpdateInput {
    readonly authorId?: number | undefined;
    readonly authorName?: string | undefined;
    readonly bio?: string | undefined;
    readonly contactInfo?: string | undefined;
    readonly githubLink?: string | undefined;
    readonly weiboLink?: string | undefined;
}
