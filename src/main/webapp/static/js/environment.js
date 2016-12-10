/**
 * Assign __env to the root window object.
 *
 * The goal of this file is to allow the deployment
 * process to pass in environment values into the application.
 *
 * The deployment process can overwrite this file to pass in
 * custom values:
 *
 * window.__env = window.__env || {};
 * window.__env.url = 'some-url';
 * window.__env.key = 'some-key';
 *
 * Keep the structure flat (one level of properties only) so
 * the deployment process can easily map environment keys to
 * properties.
 */

(function (window) {
  window.__env = window.__env || {};

 window.__env.apiUrl = 'http://localhost:8080/emv';

  // Setting this to false will disable console output
 window.__env.enableDebug = true;
 
 window.__env.timeoutValue = 1800; 
}(this));