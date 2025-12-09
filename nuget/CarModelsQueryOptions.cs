using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.CarModels
{
    /// <summary>
    /// Query options for the Car Models API
    /// </summary>
    public class CarModelsQueryOptions
    {
        /// <summary>
        /// The year of the car to get information about
        /// Example: 2020
        /// </summary>
        [JsonProperty("year")]
        public string Year { get; set; }

        /// <summary>
        /// The make of the car to get information about
        /// Example: Toyota
        /// </summary>
        [JsonProperty("make")]
        public string Make { get; set; }

        /// <summary>
        /// The model of the car to get information about
        /// Example: Camry
        /// </summary>
        [JsonProperty("model")]
        public string Model { get; set; }

        /// <summary>
        /// The trim of the car to get information about
        /// Example: LE
        /// </summary>
        [JsonProperty("trim")]
        public string Trim { get; set; }
    }
}
