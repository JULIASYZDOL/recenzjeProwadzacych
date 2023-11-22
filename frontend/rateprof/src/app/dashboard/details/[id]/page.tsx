'use client'

import { useRouter, usePathname, useSearchParams } from 'next/navigation';
import Link from 'next/link';

export default function Details() {
    const router = useRouter();
    const pathname = usePathname()
    const id = useSearchParams();

    const url = `${pathname}?${id}`
    console.log(url)

    const pseudonimy = ['Pseudonim0', 'Pseudonim1', 'Pseudonim2'];
    const komentarze = ['Treść komentarza numer 0', 'Treść komentarza numer 1', 'Treść komentarza numer 2'];

    return (
    <div>
      <button type="button" onClick={() => router.back()}>Wróć do listy prowadzących</button>
      <div className="container">
        <p>Prowadzący: </p>
        <p>Uczelnia: </p>
        <p>Średnia ocena jakości prowadzenia zajęć: </p>
        <p>Średnia ocena trudności zaliczenia zajęć: </p>
        <div className="button-container">
          <Link href={`url/ocena`}>
            <button type="button">Oceń</button>
          </Link>
          <Link href={`url/komentarz`}>
          <button type="button">Skomentuj</button>
          </Link>
        </div>
      </div>
        <h2>Komentarze: </h2>
        <ul className="comment-list">
          {pseudonimy.map((pseudonim, index) => (
            <li key={index} className="comment-item">
              <strong>Pseudonim:</strong> {pseudonim}
              <br />
              <strong>Komentarz:</strong> {komentarze[index]}
            </li>
          ))}
        </ul>
    </div>

  );
};
