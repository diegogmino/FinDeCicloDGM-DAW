import { Link } from "react-router-dom";

export default function WishlistGallery(props) {
  const { films } = props;

  return (
    <div className="bg-white">
      <div className="max-w-2xl mx-auto py-16 px-4 sm:pt-5 sm:pb-5 sm:px-6 lg:max-w-7xl lg:px-8">
        <div className="mt-6 grid grid-cols-1 gap-y-10 gap-x-6 sm:grid-cols-2 lg:grid-cols-5 xl:gap-x-8">
          {films.map((film) => (
            <div key={film.id} className="group relative">
              <div className="w-full min-h-80 bg-gray-200 rounded-md overflow-hidden group-hover:opacity-75 aspect-w-11 aspect-h-16">
                <img
                  src={film.portada}
                  alt={film.titulo}
                  className="object-center object-cover"
                />
              </div>
              <div className="mt-4 flex justify-between">
                <div>
                  <h3 className="text-sm text-gray-700">
                    <Link to={"/detalle/" + film.id}>
                      <span aria-hidden="true" className="absolute inset-0" />
                      {film.titulo}
                    </Link>
                  </h3>
                </div>
                <p className="text-sm font-medium text-gray-900">
                  {film.precio}€
                </p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
