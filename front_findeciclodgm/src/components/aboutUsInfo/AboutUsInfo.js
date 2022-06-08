import {
  PaperAirplaneIcon,
  CurrencyEuroIcon,
  FilmIcon,
  UserIcon,
} from "@heroicons/react/outline";

const features = [
  {
    name: "Los mejores precios están aquí",
    description:
      "No encontrarás mejores ofertas en ningún otro lugar. Y si las encuentras, ¡te las igualamos!",
    icon: CurrencyEuroIcon,
  },
  {
    name: "Un equipo humano",
    description:
      "Nuestra atención al cliente no te dejará de lado. Cualquier duda o problema que tengas, ¡te lo resolvemos al instante!",
    icon: UserIcon,
  },
  {
    name: "Hecho por y para cinéfilos",
    description:
      "Si amas el séptimo arte (y por eso estás leyendo esto) este es el lugar que estabas buscando.",
    icon: FilmIcon,
  },
  {
    name: "Envío rápido",
    description:
      "En el momento que termines de realizar tu pedido pondremos a nuestros minions a trabajar para que te llegue lo antes posible. (Ningún minion está siendo explotado, avisamos).",
    icon: PaperAirplaneIcon,
  },
];

export default function AboutUsInfo() {
  return (
    <div className="py-12 bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="lg:text-center">
          <h2 className="text-base text-principal font-semibold tracking-wide uppercase">
            Una tienda como ninguna otra
          </h2>
          <p className="mt-2 text-3xl leading-8 font-extrabold tracking-tight text-gray-900 sm:text-4xl">
            La sala de cine ha llegado a tu casa
          </p>
          <p className="mt-4 max-w-2xl text-xl text-gray-500 lg:mx-auto">
            Somos un grupo de amantes del cine como forma de arte que quiere que
            todo el mundo pueda disfrutar de lo que nos apasiona.
          </p>
        </div>

        <div className="mt-10">
          <dl className="space-y-10 md:space-y-0 md:grid md:grid-cols-2 md:gap-x-8 md:gap-y-10">
            {features.map((feature) => (
              <div key={feature.name} className="relative">
                <dt>
                  <div className="absolute flex items-center justify-center h-12 w-12 rounded-md bg-principal text-white">
                    <feature.icon className="h-6 w-6" aria-hidden="true" />
                  </div>
                  <p className="ml-16 text-lg leading-6 font-medium text-gray-900">
                    {feature.name}
                  </p>
                </dt>
                <dd className="mt-2 ml-16 text-base text-gray-500">
                  {feature.description}
                </dd>
              </div>
            ))}
          </dl>
        </div>
      </div>
    </div>
  );
}
