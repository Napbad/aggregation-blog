import type {Executor} from './';
import {ArticleController, FileController, UserAccountController} from './services/';

export class Api {
    
    readonly articleController: ArticleController
    
    readonly fileController: FileController
    
    readonly userAccountController: UserAccountController
    
    constructor(executor: Executor) {
        this.articleController = new ArticleController(executor);
        this.fileController = new FileController(executor);
        this.userAccountController = new UserAccountController(executor);
    }
}