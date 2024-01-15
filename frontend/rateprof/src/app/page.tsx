'use client'
import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function Start() {
  const [searchQuery, setSearchQuery] = useState('');

  const router = useRouter();

  const handleLoginClick = () => {
    router.push('/login');
  };

  return (
    <div className="container">
        <div className="container mx-auto px-6 py-12 h-full flex justify-center items-center">
          <div className="md:w-8/12 lg:w-5/12 bg-white px-8 py-10">
            <div style={{ textAlign: 'center' }}>
                <h2><strong>RateProf</strong></h2>
                <img src="favicon.ico" alt="Favicon" style={{ display: 'inline-block' }} />
            </div>
                <a
                    className="px-7 py-2 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:shadow-lg focus:shadow-lg focus:outline-none focus:ring-0 active:shadow-lg transition duration-150 ease-in-out w-full flex justify-center items-center"
                    style={{ backgroundColor: "#000000" }}
                    onClick={handleLoginClick}
                    role="button"
                >
                Zaloguj/Zarejestruj się!
                </a>
                </div></div>
    </div>
  );
}


