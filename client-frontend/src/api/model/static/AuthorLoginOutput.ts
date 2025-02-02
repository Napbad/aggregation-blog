export interface AuthorLoginOutput {
    readonly authorId: number;
    readonly authorName: string;
    readonly bio?: string | undefined;
    readonly contactInfo?: string | undefined;
    readonly email: string;
    readonly githubLink?: string | undefined;
    readonly joinedTime?: string | undefined;
    readonly msg: string;
    readonly verified: boolean;
    readonly weiboLink?: string | undefined;
}
