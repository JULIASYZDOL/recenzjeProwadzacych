'use client'
import { useState } from 'react';
import Link from 'next/link';
import { signOut } from "next-auth/react";
import { useRouter } from 'next/navigation';

export default function Home() {
  const [searchQuery, setSearchQuery] = useState('');

  const router = useRouter();

  const input_style =
    "form-control block w-full px-4 py-5 text-sm font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none";

  const handleLogout = async () => {
    await signOut({ callbackUrl: '/' });
  };

  return (
    <div>
      <header style={{ backgroundColor: '#000', padding: '10px', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <Link href="/" style={{ color: '#fff', marginRight: '10px' }}>Home</Link>
        </div>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <button type="button" style={{ color: '#fff' }} onClick={handleLogout}>Wyloguj</button>
        </div>
      </header>
      <div className="container mx-auto px-6 py-12 h-full flex justify-center items-center">
        <input
          type="text"
          placeholder="Wyszukaj uczelnię..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          className={`${input_style}`}
        />
        <Link href={`dashboard/${searchQuery}`}>
          <button
            type="submit"
            style={{ backgroundColor: `${"#3446eb"}` }}
            className="inline-block px-7 py-4 bg-blue-600 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out w-full"
          >Szukaj!
          </button>
        </Link>

      </div>
    </div>
  );
}


