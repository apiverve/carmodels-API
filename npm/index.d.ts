declare module '@apiverve/carmodels' {
  export interface carmodelsOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface carmodelsResponse {
    status: string;
    error: string | null;
    data: CarModelsData;
    code?: number;
  }


  interface CarModelsData {
      count:      number;
      filteredOn: string[];
      cars:       Car[];
  }
  
  interface Car {
      make:      string;
      cityMPG:   string;
      cityELEC:  string;
      combMPG:   string;
      combELEC:  string;
      cyl:       string;
      displace:  string;
      drive:     string;
      fuel:      string;
      highwELEC: string;
      highwMPG:  string;
      trans:     string;
      size:      string;
      year:      string;
      trim:      string;
      model:     string;
  }

  export default class carmodelsWrapper {
    constructor(options: carmodelsOptions);

    execute(callback: (error: any, data: carmodelsResponse | null) => void): Promise<carmodelsResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: carmodelsResponse | null) => void): Promise<carmodelsResponse>;
    execute(query?: Record<string, any>): Promise<carmodelsResponse>;
  }
}
