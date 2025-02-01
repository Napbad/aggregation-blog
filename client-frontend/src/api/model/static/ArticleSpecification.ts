import type {TimeSource} from './index.ts';

export interface ArticleSpecification {
    readonly articleId?: number | undefined;
    readonly authorId?: number | undefined;
    readonly category?: string | undefined;
    readonly content?: string | undefined;
    readonly copyrightInfo?: string | undefined;
    readonly maxPublishTime?: TimeSource | undefined;
    readonly maxVersionExclusive?: number | undefined;
    readonly minPublishTime?: TimeSource | undefined;
    readonly minVersionExclusive?: number | undefined;
    readonly pageNum?: number | undefined;
    readonly pageSize?: number | undefined;
    readonly title?: string | undefined;
}
