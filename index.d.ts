declare module '@apiverve/carmodels' {
  export interface carmodelsOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface carmodelsResponse {
    status: string;
    error: string | null;
    data: any;
    code?: number;
  }

  export default class carmodelsWrapper {
    constructor(options: carmodelsOptions);

    execute(callback: (error: any, data: carmodelsResponse | null) => void): Promise<carmodelsResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: carmodelsResponse | null) => void): Promise<carmodelsResponse>;
    execute(query?: Record<string, any>): Promise<carmodelsResponse>;
  }
}
