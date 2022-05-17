import padrino from '../../img/portadas/el-padrino.jpg';
import samurais from '../../img/portadas/los-siete-samurais.jpg';
import cuervo from '../../img/portadas/el-cuervo.jpg';
import blade from '../../img/portadas/blade-runner.jpg';
import anillos from '../../img/portadas/el-señor-de-los-anillos.webp';
import pulp from '../../img/portadas/pulp-fiction.jpg';
import taxi from '../../img/portadas/taxi-driver.jpg';

import { Link } from 'react-router-dom';

export default function Promo() {
    return (
      <div className="relative bg-white overflow-hidden">
        <div className="pt-16 pb-80 sm:pt-24 sm:pb-40 lg:pt-40 lg:pb-48">
          <div className="relative max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 sm:static">
            <div className="sm:max-w-lg">
              <h1 className="text-4xl font font-extrabold tracking-tight text-gray-900 sm:text-6xl">
                La mejor forma de comprar cine
              </h1>
              <p className="mt-4 text-xl text-gray-500">
                Nunca ha sido tan sencillo adquirir cultura cinematográfica como 
                hoy en día. Lánzate a comprar el mejor cine al mejor precio y prepara
                la manta y las palomitas para esta noche.
              </p>
            </div>
            <div>
              <div className="mt-10">
                {/* Grid de portadas */}
                <div
                  aria-hidden="true"
                  className="pointer-events-none lg:absolute lg:inset-y-0 lg:max-w-7xl lg:mx-auto lg:w-full"
                >
                  <div className="absolute transform sm:left-1/2 sm:top-0 sm:translate-x-8 lg:left-1/2 lg:top-1/2 lg:-translate-y-1/2 lg:translate-x-8">
                    <div className="flex items-center space-x-6 lg:space-x-8">
                      <div className="flex-shrink-0 grid grid-cols-1 gap-y-6 lg:gap-y-8">
                        <div className="w-44 h-64 rounded-lg overflow-hidden sm:opacity-0 lg:opacity-100">
                          <img
                            src={padrino}
                            alt="El padrino"
                            className="w-full h-full object-center object-cover"
                          />
                        </div>
                        <div className="w-44 h-64 rounded-lg overflow-hidden">
                          <img
                            src={samurais}
                            alt="Los siete samuráis"
                            className="w-full h-full object-center object-cover"
                          />
                        </div>
                      </div>
                      <div className="flex-shrink-0 grid grid-cols-1 gap-y-6 lg:gap-y-8">
                        <div className="w-44 h-64 rounded-lg overflow-hidden">
                          <img
                            src={cuervo}
                            alt="El cuervo"
                            className="w-full h-full object-center object-cover"
                          />
                        </div>
                        <div className="w-44 h-64 rounded-lg overflow-hidden">
                          <img
                            src={blade}
                            alt="Blade Runner"
                            className="w-full h-full object-center object-cover"
                          />
                        </div>
                        <div className="w-44 h-64 rounded-lg overflow-hidden">
                          <img
                            src={anillos}
                            alt="El señor de los anillos"
                            className="w-full h-full object-center object-cover"
                          />
                        </div>
                      </div>
                      <div className="flex-shrink-0 grid grid-cols-1 gap-y-6 lg:gap-y-8">
                        <div className="w-44 h-64 rounded-lg overflow-hidden">
                          <img
                            src={pulp}
                            alt="Pulp Fiction"
                            className="w-full h-full object-center object-cover"
                          />
                        </div>
                        <div className="w-44 h-64 rounded-lg overflow-hidden">
                          <img
                            src={taxi}
                            alt="Taxi Driver"
                            className="w-full h-full object-center object-cover"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
  
                <Link
                  to="/peliculas"
                  className="inline-block text-center bg-principal border border-transparent rounded-md py-3 px-8 font-medium text-white hover:bg-gris-oscuro hover:text-white"
                >
                  Compra ya
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
  