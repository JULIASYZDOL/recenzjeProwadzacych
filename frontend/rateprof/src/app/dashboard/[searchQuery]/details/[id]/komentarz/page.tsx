'use client'

import { usePathname, useRouter, useSearchParams } from 'next/navigation';
import { useEffect, useState } from 'react';

export default function Komentarz() {
  const router = useRouter();
  const [pseudonimQuery, setPseudonimQuery] = useState('');
  const [trescQuery, setTrescQuery] = useState('');
  const pathname = usePathname();
  const params = useSearchParams();
  const [id, setId] = useState<string | null>(null);

  useEffect(() => {
    const idFromParams = params.get('id');
    console.log(idFromParams);

    if (idFromParams) {
      setId(idFromParams);
    }
  }, [params]);

  const handlePublish = async () => {
    try {
      const apiPath = 'http://localhost:8080/komentarze/AddKomentarz';

      const string_url = `${pathname}?${id}`;
      const url = new URL("http://localhost:3000" + string_url);

      const prowadzacy = decodeURIComponent(url.pathname.split('/')[4]);

      const res = await fetch(`http://localhost:8080/prowadzacy/byNazwa/${prowadzacy}`);
      if (!res.ok) {
        throw new Error('Failed to fetch data');
      }
      const idProw = await res.json();
      console.log('Fetched idProw:', idProw);

      const commentData = [{
        id: Number,
        tytul: "Komentarz",
        tresc: trescQuery,
        pseudonim: pseudonimQuery,
        idProwadzacego: idProw
      }];

      const response = await fetch(apiPath, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(commentData),
      });

      if (!response.ok) {
        throw new Error('Failed to publish comment');
      }
      router.back();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="container">
      <h1>Dodajesz komentarz</h1>
      <input
        type="text"
        placeholder="Twój pseudonim"
        value={pseudonimQuery}
        onChange={(e) => setPseudonimQuery(e.target.value)}
      />
      <input
        type="text"
        placeholder="Treść komentarza"
        value={trescQuery}
        onChange={(e) => setTrescQuery(e.target.value)}
      />
      <div className="button-container">
        <button type="button" onClick={handlePublish}>Opublikuj</button>
        <button type="button" onClick={() => router.back()}>Anuluj</button>
      </div>
    </div>
  );
}
