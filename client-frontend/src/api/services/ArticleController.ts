import type {Executor} from '../index.ts';
import type {
    ArticleCreateInput, 
    ArticleCreateOutput, 
    ArticleDeleteInput, 
    ArticleQueryOutput, 
    ArticleSpecification, 
    ArticleUpdateInput, 
    ArticleUpdateOutput, 
    Page
} from '../model/static';

export class ArticleController {
    
    constructor(private executor: Executor) {}
    
    readonly add: (options: ArticleControllerOptions['add']) => Promise<
        ArticleCreateOutput
    > = async(options) => {
        let _uri = '/article-account/add';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<ArticleCreateOutput>;
    }
    
    readonly delete: (options: ArticleControllerOptions['delete']) => Promise<
        string
    > = async(options) => {
        let _uri = '/article-account/delete';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<string>;
    }
    
    readonly getArticle: (options: ArticleControllerOptions['getArticle']) => Promise<
        Page<ArticleQueryOutput>
    > = async(options) => {
        let _uri = '/article-account/query';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<Page<ArticleQueryOutput>>;
    }
    
    readonly update: (options: ArticleControllerOptions['update']) => Promise<
        ArticleUpdateOutput
    > = async(options) => {
        let _uri = '/article-account/update';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<ArticleUpdateOutput>;
    }
}

export type ArticleControllerOptions = {
    'add': {
        readonly body: ArticleCreateInput
    }, 
    'getArticle': {
        readonly body: ArticleSpecification
    }, 
    'update': {
        readonly body: ArticleUpdateInput
    }, 
    'delete': {
        readonly body: ArticleDeleteInput
    }
}
