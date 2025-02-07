import type {Executor} from '../';
import type {
    AuthorLoginInput, 
    AuthorLoginResult, 
    AuthorRegisterInput, 
    AuthorRegisterOutput, 
    AuthorUpdateInput, 
    AuthorUpdateOutput, 
    AuthorVerifyInput
} from '../model/static/';

export class UserAccountController {
    
    constructor(private executor: Executor) {}
    
    readonly getCaptcha: (options: UserAccountControllerOptions['getCaptcha']) => Promise<
        string
    > = async(options) => {
        let _uri = '/user-account/get-captcha';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<string>;
    }
    
    readonly login: (options: UserAccountControllerOptions['login']) => Promise<
        AuthorLoginResult
    > = async(options) => {
        let _uri = '/user-account/login';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<AuthorLoginResult>;
    }
    
    readonly logout: () => Promise<
        string
    > = async() => {
        let _uri = '/user-account/logout';
        return (await this.executor({uri: _uri, method: 'POST'})) as Promise<string>;
    }
    
    readonly register: (options: UserAccountControllerOptions['register']) => Promise<
        AuthorRegisterOutput
    > = async(options) => {
        let _uri = '/user-account/register';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<AuthorRegisterOutput>;
    }
    
    readonly update: (options: UserAccountControllerOptions['update']) => Promise<
        AuthorUpdateOutput
    > = async(options) => {
        let _uri = '/user-account/update';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<AuthorUpdateOutput>;
    }
}

export type UserAccountControllerOptions = {
    'register': {
        readonly body: AuthorRegisterInput
    }, 
    'login': {
        readonly body: AuthorLoginInput
    }, 
    'getCaptcha': {
        readonly body: AuthorVerifyInput
    }, 
    'logout': {}, 
    'update': {
        readonly body: AuthorUpdateInput
    }
}
