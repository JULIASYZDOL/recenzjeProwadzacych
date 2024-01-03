'use client'

import { useRouter, usePathname, useSearchParams } from 'next/navigation';
import { useState } from 'react';
import { signOut } from "next-auth/react";
import Link from 'next/link';

export default function Ocena() {
  const router = useRouter();
  const pathname = usePathname()
  const id = useSearchParams();

  const url = `${pathname}?${id}`
  console.log(url)

  const [ocenaJakości, setOcenaJakości] = useState<string | null>(null);
  const [ocenaTrudności, setOcenaTrudności] = useState<string | null>(null);

  const handlePublish = async () => {
    if (ocenaJakości !== null && ocenaTrudności !== null) {
      const apiPathJak = 'http://localhost:8080/ocenyJakosci/AddOcenaJak';
      const apiPathTru = 'http://localhost:8080/ocenyTrudnosci/AddOcenaTru';
  
      const string_url = `${pathname}?${id}`;
      const url = new URL("http://localhost:3000" + string_url);
  
      const prowadzacy = decodeURIComponent(url.pathname.split('/')[4]);
  
      const res = await fetch(`http://localhost:8080/prowadzacy/byNazwa/${prowadzacy}`);
      if (!res.ok) {
        throw new Error('Failed to fetch data');
      }
      const idProw = await res.json();
      console.log('Fetched idProw:', idProw);

      try {  
        const ocenaJakData = [{
          id: Number,
          idProwadzacego: idProw,
          wartosc: ocenaJakości
        }];
  
        const response = await fetch(apiPathJak, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(ocenaJakData),
        });
  
        if (!response.ok) {
          throw new Error('Failed to publish ocena jakości');
        }
      } catch (error) {
        console.error(error);
      }

      try {  
        const ocenaTruData = [{
          id: Number,
          idProwadzacego: idProw,
          wartosc: ocenaTrudności
        }];
  
        const response = await fetch(apiPathTru, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(ocenaTruData),
        });
  
        if (!response.ok) {
          throw new Error('Failed to publish ocena trudności');
        }
      } catch (error) {
        console.error(error);
      }

      router.back()
    } else {
      console.error('Proszę wybrać oceny dla obu kategorii.');
    }
  };
  
  const handleLogout = async () => {
    await signOut({ callbackUrl: '/' });
  };

  return (
    <div>
    <header style={{ backgroundColor: '#000', padding: '10px', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <Link href="/" style={{ color: '#fff', marginRight: '10px' }}>Home</Link>
          <button type="button" style={{ color: '#fff', marginRight: '10px' }} onClick={() => router.back()}>Wróć do strony prowadzącego</button>
        </div>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <Link href="/profile" style={{ color: '#fff', marginRight: '10px' }}>Profil</Link>
          <button type="button" style={{ color: '#fff' }} onClick={handleLogout}>Wyloguj</button>
        </div>
      </header>
    <div className="container mx-auto px-6 py-12 h-full flex justify-center items-center">
        <div className="md:w-8/12 lg:w-5/12 bg-white px-8 py-10">
        <h4><strong>Twoja ocena jakości prowadzenia zajęć:</strong></h4>
        <div className="flex justify-center items-center">
        <form className="star-rating">
          <input className="radio-input" type="radio" id="star5" name="star-input" value="5" onChange={(e) => setOcenaJakości(e.target.value)}/>
          <label className="radio-label" htmlFor="star5" title="5 gwiazdek">5 gwiazdek</label>

          <input className="radio-input" type="radio" id="star4" name="star-input" value="4" onChange={(e) => setOcenaJakości(e.target.value)}/>
          <label className="radio-label" htmlFor="star4" title="4 gwiazdki">4 gwiazdki</label>

          <input className="radio-input" type="radio" id="star3" name="star-input" value="3" onChange={(e) => setOcenaJakości(e.target.value)}/>
          <label className="radio-label" htmlFor="star3" title="3 gwiazdki">3 gwiazdki</label>

          <input className="radio-input" type="radio" id="star2" name="star-input" value="2" onChange={(e) => setOcenaJakości(e.target.value)}/>
          <label className="radio-label" htmlFor="star2" title="2 gwiazdki">2 gwiazdki</label>

          <input className="radio-input" type="radio" id="star1" name="star-input" value="1" onChange={(e) => setOcenaJakości(e.target.value)}/>
          <label className="radio-label" htmlFor="star1" title="1 gwiazdka">1 gwiazdka</label>
        </form>
        </div>
        <h4><strong>Twoja ocena trudności zaliczenia zajęć:</strong></h4>
        <div className="flex justify-center items-center">
        <form className="star-rating">
          <input className="radio-input" type="radio" id="star25" name="star-input" value="5" onChange={(e) => setOcenaTrudności(e.target.value)}/>
          <label className="radio-label" htmlFor="star25" title="5 gwiazdek">5 gwiazdek</label>

          <input className="radio-input" type="radio" id="star24" name="star-input" value="4" onChange={(e) => setOcenaTrudności(e.target.value)}/>
          <label className="radio-label" htmlFor="star24" title="4 gwiazdki">4 gwiazdki</label>

          <input className="radio-input" type="radio" id="star23" name="star-input" value="3" onChange={(e) => setOcenaTrudności(e.target.value)}/>
          <label className="radio-label" htmlFor="star23" title="3 gwiazdki">3 gwiazdki</label>

          <input className="radio-input" type="radio" id="star22" name="star-input" value="2" onChange={(e) => setOcenaTrudności(e.target.value)}/>
          <label className="radio-label" htmlFor="star22" title="2 gwiazdki">2 gwiazdki</label>

          <input className="radio-input" type="radio" id="star21" name="star-input" value="1" onChange={(e) => setOcenaTrudności(e.target.value)}/>
          <label className="radio-label" htmlFor="star21" title="1 gwiazdka">1 gwiazdka</label>
        </form>
        </div>
        <div className="button-container">
          <button
              type="submit"
              style={{ backgroundColor: `${"#3446eb"}` }}
              className="inline-block px-7 py-4 bg-blue-600 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out w-full"
              onClick={handlePublish}
            >Zatwierdź
            </button>
          <button
              type="submit"
              style={{ backgroundColor: `${"#3446eb"}` }}
              className="inline-block px-7 py-4 bg-blue-600 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out w-full"
              onClick={() => router.back()}
            >Anuluj
            </button>
        </div>
        </div>
    </div>
    </div>
  )
}