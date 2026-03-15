function fn() {
  var baseUrl = karate.properties['karate.baseUrl'] || 'http://localhost:8081';
  return {
    baseUrl: baseUrl,
    apiPath: '/api/v1/inventory'
  };
}
