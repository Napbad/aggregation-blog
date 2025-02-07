import type {Executor} from '../';
import type {CompletableFuture} from '../model/static/';

export class FileController {
    
    constructor(private executor: Executor) {}
    
    readonly uploadFile: (options: FileControllerOptions['uploadFile']) => Promise<
        CompletableFuture<string>
    > = async(options) => {
        let _uri = '/files/upload';
        const _formData = new FormData();
        const _body = options.body;
        _formData.append("file", _body.file);
        return (await this.executor({uri: _uri, method: 'POST', body: _formData})) as Promise<CompletableFuture<string>>;
    }
}

export type FileControllerOptions = {
    'uploadFile': {
        readonly body: {
            readonly file: File
        }
    }
}
